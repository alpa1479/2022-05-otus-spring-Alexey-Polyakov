import {useEffect, useState} from 'react';
import {useLocation, useNavigate} from 'react-router-dom';

import BookForm from '../../forms/BookForm';
import {getAuthors} from "../../services/api/authorsService";
import {getGenres} from "../../services/api/genresService";
import {create} from "../../services/api/restService";

function AddBookPage() {
    const navigate = useNavigate();
    const location = useLocation();

    const bookLinks = location.state;
    const [book] = useState({title: '', authors: [], genre: ''});
    const [genres, setGenres] = useState([]);
    const [authors, setAuthors] = useState([]);

    useEffect(() => {
        Promise.all([getGenres(), getAuthors()])
            .then(([genresResponse, authorsResponse]) => {
                setGenres(genresResponse.data._embedded.genres);
                setAuthors(authorsResponse.data._embedded.authors);
            })
            .catch(error => console.error(error));
    }, []);

    const handleSubmit = (event, bookToSave) => {
        event.preventDefault();
        create(bookLinks.self.href, bookToSave)
            .then(() => navigate('/'))
            .catch(error => console.error(error));
    };

    return (
        <BookForm isUpdate={false} book={book} genres={genres} authors={authors} handleSubmit={handleSubmit}/>
    );
}

export default AddBookPage;