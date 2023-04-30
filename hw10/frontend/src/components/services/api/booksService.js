import axios from 'axios';

const BASE_URL = '/api/v1';

const getBooks = () => {
    return axios.get(`${BASE_URL}/books`);
};

const getBookById = (id) => {
    return axios.get(`${BASE_URL}/books/${id}`);
};

const createBook = (body) => {
    return axios.post(`${BASE_URL}/books`, body);
};

const updateBook = (body) => {
    return axios.put(`${BASE_URL}/books`, body);
};

const deleteBook = (id) => {
    return axios.delete(`${BASE_URL}/books/${id}`);
};

export {getBooks, getBookById, createBook, updateBook, deleteBook};