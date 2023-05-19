import axios from 'axios';

const BASE_URL = '/api';

const getBooks = () => {
    return axios.get(`${BASE_URL}/books`);
};

export {getBooks};