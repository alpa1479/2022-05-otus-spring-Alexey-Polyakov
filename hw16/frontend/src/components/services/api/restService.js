import axios from "axios";

const create = (href, body) => {
    return axios.post(new URL(href).pathname, body);
};

const patch = (href, body) => {
    return axios.patch(new URL(href).pathname, body);
};

const remove = (href) => {
    return axios.delete(new URL(href).pathname);
};

export {create, patch, remove};