import User from './User.js';
import Btn from './Btn.js';

const modalElement = document.getElementById("user-modal");
const modal = new bootstrap.Modal(modalElement);

const triggerEl = document.querySelector('#home-tab');

const BASE_URL = '/api/v1/admin';
const API_URL = {
    all: BASE_URL,
    get: `${BASE_URL}/get/`,
    add: `${BASE_URL}/add`,
    edit: `${BASE_URL}/edit`,
    delete: `${BASE_URL}/delete/`,
}

const _props = {
    method: "POST",
    headers: {
        "Content-Type": "application/json"
    }
};

const _fetch = async (url, props = {}) => {
    const data = await fetch(url, {..._props, ...props});
    return await data.json();
};

const buttons = {
    Edit: "btn btn-primary",
    Delete: "btn btn-danger"
};

const createButton = ({dataset = {}, ...props}) => {
    const btn = document.createElement("button");
    Object.assign(btn, props);
    Object.assign(btn.dataset, dataset);
    return btn;
}

const resetInput = () => {
    document.querySelectorAll('.invalid-feedback').forEach(e => {
        e.parentElement.querySelector('.is-invalid').classList.remove('is-invalid');
        e.remove();
    });
};

const fill = (e, disabled = false) => {
    e.preventDefault();
    _fetch(`${API_URL.get}${e.target.dataset.userId}`)
        .then((data) => {
            const user = data['payload'];
            modalElement.querySelector("input[class='form-control not']").value = user.id;
            for(const [key, value] of Object.entries(new User({...user}) )) {
                const input = modalElement.querySelector("input[name='" + key + "']");
                if (input) input.value = value;
            }
            modalElement.querySelectorAll("#roles option").forEach((option) => {
                option.selected = user.roles.some(role => "" + role.id === option.value);
            });
            const btn = modalElement.querySelector("[type='submit']");
            btn.innerText = e.target.dataset.btn;
            btn.className = e.target.classList["value"];
            btn.dataset.submit = e.target.dataset.btn;
            btn.dataset.userId = e.target.dataset.userId;
            modalElement.querySelectorAll(".form-control:not(.not)").forEach(e => e.disabled = disabled);
            modalElement.querySelector(".modal-title").innerText = e.target.dataset.btn + " user";
        });
    modal.show();
};

const fillTable = data => {
    const tbody = document.querySelector("#users-tbody");
    tbody.innerHTML = "";
    data['payload'].forEach((user) => {
        const row = document.createElement("tr");
        for (const [key, value] of Object.entries(new User({...user}))) {
            const cell = document.createElement("td");
            cell.innerText = value;
            row.append(cell);
        }
        for (const [key, value] of Object.entries(buttons)) {
            const button = new Btn({text: key, className: value, userId: user.id});
            const cell = document.createElement("td");
            cell.append(createButton({...button}));
            row.append(cell);
        }
        tbody.append(row);
    });
};

const submitForm = (url, _title, trigger = () => {}, form = null) => {
    let dataObject = null;
    if(form != null) {
        const formData = new FormData(form);
        dataObject = Object.fromEntries(formData.entries());
        dataObject['roles'] = formData.getAll('roles') !== undefined ? formData.getAll('roles') : [];
    }
    _fetch(url, {body:  JSON.stringify(dataObject)})
        .then(data => {
            if (data['success'] === true) {
                trigger();
                initTable();
            } else {
                if(data['error'] !== undefined && data['error'] !== null) {
                    resetInput();
                    for(const [key, value] of Object.entries(data['error'])) {
                        const el = form.querySelector("[name='" + key + "']");
                        el.classList.add('is-invalid');
                        const p = document.createElement("p");
                        p.classList.add('invalid-feedback');
                        p.innerText = value;
                        el.after(p);
                    }
                }
            }
            new Notify({title: _title, text: data.message});
        })
        .catch(err => {
            new Notify({title: _title, text: err.message, status: "error"});
        });
};


const initTable = () => {_fetch(API_URL.all).then(fillTable);};

const handler = (e) => {
    const target = e.target;
    if(target.dataset.btn === "Edit") {
        fill(e,false);
    } else if(target.dataset.btn === "Delete") {
        fill(e,true);
    } else if(target.dataset.submit === "Add") {
        e.preventDefault();
        const form = document.querySelector("#index-add-form");
        submitForm(API_URL.add, "Add user", () => {triggerEl.click(); form.reset();}, form)
    } else if(target.dataset.submit === "Edit") {
        e.preventDefault();
        const form = modalElement.querySelector("form");
        submitForm(API_URL.edit, 'Edit user', () => {modal.hide(); form.reset();}, form);
    } else if(target.dataset.submit === "Delete") {
        e.preventDefault();
        submitForm(`${API_URL.delete}${target.dataset.userId}`, "Delete user", () => {modal.hide()})
    } else if(target.matches(".close-modal-dialog")) {
        e.target.blur();
        modal.hide();
    }
};

window.onload = e => {
    document.addEventListener("click", handler);
    modalElement.addEventListener("hide.bs.modal", e => {
        document.activeElement.blur();
        resetInput();
    });
    initTable();
};