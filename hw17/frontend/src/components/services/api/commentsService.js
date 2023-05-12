import axios from 'axios';

const BASE_URL = '/api/v1';

const addComment = (data) => {
    return axios.post(`${BASE_URL}/comments`, data);
};

const deleteComments = (id) => {
    return axios.delete(`${BASE_URL}/comments/books/${id}`);
};

export {addComment, deleteComments};