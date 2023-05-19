import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';

import './App.css';
import BooksPage from './components/pages/BooksPage/BooksPage';
import AddBookPage from "./components/pages/AddBookPage/AddBookPage";
import UpdateBookPage from "./components/pages/UpdateBookPage/UpdateBookPage";

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<BooksPage/>}/>
                <Route path="/add-book" element={<AddBookPage/>}/>
                <Route path="/update-book" element={<UpdateBookPage/>}/>
            </Routes>
        </Router>
    );
}

export default App;
