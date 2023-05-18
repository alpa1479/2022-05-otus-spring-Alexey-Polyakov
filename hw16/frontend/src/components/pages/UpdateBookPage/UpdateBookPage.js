import {useEffect, useState} from 'react';
import {useLocation, useNavigate} from 'react-router-dom';

import BookForm from '../../forms/BookForm';
import {getAuthors} from "../../services/api/authorsService";
import {getGenres} from "../../services/api/genresService";
import {patch} from "../../services/api/restService";

function UpdateBookPage() {
    const navigate = useNavigate();
    const location = useLocation();

    const bookFromState = location.state;
    const [book, setBook] = useState({title: '', authors: [], genre: ''});
    const [genres, setGenres] = useState([]);
    const [authors, setAuthors] = useState([]);

    useEffect(() => {
        Promise.all([getGenres(), getAuthors()])
            .then(([genresResponse, authorsResponse]) => {
                const genresResponseBody = genresResponse.data._embedded.genres;
                const authorsResponseBody = authorsResponse.data._embedded.authors;
                setBook({
                    ...bookFromState,
                    genre: genresResponseBody.find(genre => genre.name === bookFromState.genre.name)?._links?.self?.href,
                    authors: bookFromState.authors.map(bookAuthor => authorsResponseBody.find(author => author.name === bookAuthor.name)?._links?.self?.href)
                });
                setGenres(genresResponseBody);
                setAuthors(authorsResponseBody);
            })
            .catch(error => console.error(error));
    }, [bookFromState]);

    const handleSubmit = (event, bookToSave) => {
        event.preventDefault();
        patch(book._links.self.href, bookToSave)
            .then(() => navigate('/'))
            .catch(error => console.error(error));
    };

    return (
        book.title &&
        <BookForm isUpdate={true} book={book} genres={genres} authors={authors} handleSubmit={handleSubmit}/>
    );
}

export default UpdateBookPage;