import {useEffect, useState} from 'react';
import {useNavigate} from 'react-router-dom';

import BookForm from '../../forms/BookForm';
import {getAuthors} from "../../services/api/authorsService";
import {getGenres} from "../../services/api/genresService";
import {createBook} from "../../services/api/booksService";

function AddBookPage() {
    const [book] = useState({title: '', authorIds: [], genreId: ''});
    const [genres, setGenres] = useState([]);
    const [authors, setAuthors] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        Promise.all([getGenres(), getAuthors()])
            .then(([genresResponse, authorsResponse]) => {
                setGenres(genresResponse.data);
                setAuthors(authorsResponse.data);
            })
            .catch(error => console.error(error));
    }, []);

    const handleSubmit = (event, bookToSave) => {
        event.preventDefault();
        createBook(bookToSave)
            .then(() => navigate('/'))
            .catch(error => console.error(error));
    };

    return (
        <BookForm book={book} genres={genres} authors={authors} handleSubmit={handleSubmit}/>
    );
}

export default AddBookPage;