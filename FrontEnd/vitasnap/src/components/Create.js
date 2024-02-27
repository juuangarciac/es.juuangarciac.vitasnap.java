import React from "react";
import {Link} from 'react-router-dom';

class Create extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            nombre:"",
            correo:""
        }
    }
    
    state = {  }

    cambioValor = (e) =>{
        const state = this.state;
        state[e.target.name] = e.target.value;
        this.setState({state});
    }

    enviarDatos = (e)=>{
        e.preventDefault();
        console.log("Formulario fue enviado...");

        const{nombre, correo} = this.state;

        console.log(nombre);
        console.log(correo);
    }

    render() { 

        const{nombre, correo} = this.state;
        
        return ( 
            <div className="card">
                <div className="card-header">
                    Empleados
                </div>
                <div className="card-body">
                    <form onSubmit={this.enviarDatos}>
                        <div class="form-group">
                            <label htmlFor="">Nombre</label>
                            <input type="text" name="nombre" onChange={this.cambioValor} id="nombre" value={nombre} class="form-control" placeholder="" aria-describedby="helpId" />
                        <small id="helpId" class="text-muted">Escribe el nombre del empleado</small>
                        </div>
                        <div class="form-group">
                            <label htmlFor="">Correo</label>
                            <input type="text" name="correo" onChange={this.cambioValor} id="correo"  value={correo} class="form-control" placeholder="" aria-describedby="helpId" />
                        <small id="helpId" class="text-muted">Escribe el correo del empleado</small>
                        </div>

                        <div class="btn-group" role="group" aria-label="">
                            <button type="submit" class="btn btn-success">Agregar empleado</button>
                            <Link to={"/"} type="button" class="btn btn-danger">Cancelar</Link>
                        </div>

                    </form>
                </div>
                <div className="card-footer text-muted">
                </div>
            </div>
        );
    }
}
 
export default Create;