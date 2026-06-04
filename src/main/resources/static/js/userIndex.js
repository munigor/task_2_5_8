import User from "./User.js";


const BASE_URL = "/api/v1/ui";

const _props = {
    method: "GET",
    headers: {
        "Content-Type": "application/json"
    }
};

const _fetch = async (url, props = {}) => {
    const data = await fetch(url, {..._props, ...props});
    return await data.json();
};

const fillTable = user => {
    const tbody = document.querySelector("#userInfo");
    tbody.innerHTML = "";
    if(typeof user !== "undefined" && user !== null ) {
        const row = document.createElement("tr");
        for (const [key, value] of Object.entries(new User({...user}))) {
            const cell = document.createElement("td");
            cell.innerText = value;
            row.append(cell);
        }
        tbody.append(row);
    }
};

const initTable = () => {
    _fetch(`${BASE_URL}/user`).then(fillTable);
};

document.addEventListener("DOMContentLoaded", () => {
    initTable()
});