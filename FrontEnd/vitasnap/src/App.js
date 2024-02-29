import './App.css';

import List from "./components/crud/List";
import Create from './components/crud/Create';
import Update from './components/crud/Update';

import {BrowserRouter as Router, Routes, Route} from "react-router-dom";
import {Link} from "react-router-dom";
import Login from './components/login/Login';

function App() {


  return (
    <Router>
       <nav className="navbar navbar-expand navbar-light bg-light">
              <div className="nav navbar-nav">
                  <Link className="nav-item nav-link active" to={"/"}>Home</Link>
              </div>
        </nav>

      <div className="container">
        <br></br>
          <Routes>
            <Route path="/" element={<List />} />
            <Route path="/create" element={<Create />} />
            <Route path='/update' element={<Update />} />
            <Route path='/login' element={<Login />} />
          </Routes>
      </div>

    </Router>
  );
}

export default App;
