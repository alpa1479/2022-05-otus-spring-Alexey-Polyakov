import axios from 'axios';

const BASE_URL = '/api';

const getAuthors = () => {
    return axios.get(`${BASE_URL}/authors`);
};

export {getAuthors};