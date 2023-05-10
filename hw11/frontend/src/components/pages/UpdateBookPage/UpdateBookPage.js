import {useEffect, useState} from 'react';
import {useNavigate} from 'react-router-dom';

import BookForm from '../../forms/BookForm';
import {getAuthors} from "../../services/api/authorsService";
import {getGenres} from "../../services/api/genresService";
import {getBookById, updateBook} from "../../services/api/booksService";

function UpdateBookPage() {
    const [book, setBook] = useState({title: '', authorIds: [], commentIds: [], genreId: ''});
    const [genres, setGenres] = useState([]);
    const [authors, setAuthors] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        Promise.all([getGenres(), getAuthors(), getBookById(new URLSearchParams(window.location.search).get("id"))])
            .then(([genresResponse, authorsResponse, bookResponse]) => {
                setGenres(genresResponse.data);
                setAuthors(authorsResponse.data);
                setBook(bookResponse.data);
            })
            .catch(error => console.error(error));
    }, []);

    const handleSubmit = (event, bookToSave) => {
        event.preventDefault();
        updateBook(bookToSave)
            .then(() => navigate('/'))
            .catch(error => console.error(error));
    };

    return (
        book.id && <BookForm book={book} genres={genres} authors={authors} handleSubmit={handleSubmit}/>
    );
}

export default UpdateBookPage;