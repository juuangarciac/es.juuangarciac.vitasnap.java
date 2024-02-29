import React from "react";
import './Login.css';

import {BrowserRouter as Router, Routes, Route} from "react-router-dom";
import {Link} from "react-router-dom";

class Login extends React.Component {
  constructor(props) {
      super(props);
      this.state = {
          username:"",
          password:""
      }
  }


  validateRequest() {

  }

   render () {
    return (
      <div className="loginForm">
      <div class="card">
        <div class="card-header">
        <h1>VitaSnap</h1>
        </div>
        <div class="card-body">
            <form>
                <div class="form-group">
                  <input type="text" name="username" id="username" class="form-control" placeholder="Nombre de usuario" aria-describedby="helpId" />
                  <small id="helpId" class="text-muted"></small>
                </div>
                <br/>
                <div class="form-group">
                  <input type="text" name="password" id="password" class="form-control" placeholder="Contraseña" aria-describedby="helpId" />
                  <small id="helpId" class="text-muted"></small>
                </div>
                <br/>
                <button type="submit" class="btn btn-success">Entrar</button>
            </form>
        </div>
        <div class="card-footer">
            <p>Iniciar Sesión con Google</p>
            <p>Iniciar Sesión con Facebook</p>
        </div>
      </div>
      </div>
    );
}
}

export default Login;
