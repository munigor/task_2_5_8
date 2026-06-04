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

const getUser = user => {
    if (typeof user.username !== 'undefined' && user.username !== null &&
        typeof user.rolesAsString !== 'undefined' && user.rolesAsString !== null) {
        return `${user.username} with roles ${user.rolesAsString}`;
    }
    return '';
};

const renderUser = str => document.getElementById('main-logo').setHTMLUnsafe(str);

const setUser = () => {
    _fetch(`${BASE_URL}/user`)
        .then(getUser)
        .then(renderUser);
};

const getSideHtml = data => {
    let html = "";
    data.forEach((item) => {
        const isActive = location.pathname.startsWith(item.url) ? "active" : "";
        html += `<li class="nav-item nav-pills w-100">
            <a href="${item.url}" class="nav-link align-middle px-0 btn btn-primary rounded-0 w-100 ${isActive}"
                <b>&#9711;</b><span class="ms-1 d-none d-sm-inline">${item.name}</span>
            </a>
        </li>`;
    });
    return html;
};

const renderSide = html => document.getElementById('side-menu').setHTMLUnsafe(html);

const setSide = () => {
    _fetch(`${BASE_URL}/side`)
        .then(getSideHtml)
        .then(renderSide);
};

document.addEventListener("DOMContentLoaded", () => {
    setUser();
    setSide();
})