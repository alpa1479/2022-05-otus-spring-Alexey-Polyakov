import axios from 'axios';

const BASE_URL = '/api';

const getGenres = () => {
    return axios.get(`${BASE_URL}/genres`);
};

export {getGenres};