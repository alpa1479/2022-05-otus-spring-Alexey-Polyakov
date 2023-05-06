import axios from 'axios';

const BASE_URL = '/api/v1';

const getGenres = () => {
    return axios.get(`${BASE_URL}/genres`);
};

export {getGenres};