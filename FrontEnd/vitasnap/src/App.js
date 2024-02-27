import './App.css';

import List from "./components/List";
import Create from './components/Create';
import Update from './components/Update';

import {BrowserRouter as Router, Routes, Route} from "react-router-dom";
import {Link} from "react-router-dom";

function App() {
  return (
    <Router>
       <nav className="navbar navbar-expand navbar-light bg-light">
              <div className="nav navbar-nav">
                  <Link className="nav-item nav-link active" to={"/"}>Home</Link>
                  <Link className="nav-item nav-link" to={"/create"}>Crear Empleado</Link>
                  <Link className="nav-item nav-link" to={"/update"}>Editar Empleado</Link>
              </div>
          </nav>

      <div className="container">
        <br></br>
          <Routes>
            <Route path="/" element={<List />} />
            <Route path="/create" element={<Create />} />
            <Route path='/update' element={<Update />} />
          </Routes>
      </div>

    </Router>
  );
}

export default App;
