import axios from 'axios';

const BASE_URL = '/api/v1';

const getAuthors = () => {
    return axios.get(`${BASE_URL}/authors`);
};

export {getAuthors};