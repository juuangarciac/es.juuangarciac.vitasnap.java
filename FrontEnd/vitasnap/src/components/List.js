import React from 'react';
import {Link} from "react-router-dom";

class List extends React.Component {
    constructor(props) {
        super(props);
        this.state = { 
            datosCargados:false,
            employers:[]
        }
    }
    state = {  }

    loadData(){
        fetch("http://localhost:5000/api/users", {
            method: 'POST',
            mode: 'cors',
            headers: {
                "X-API-KEY": "Baeldung"
            }
        })
        .then(response => response.json())
        .then((data) => {
            console.log(data)
            this.setState({datosCargados:true, employers:data})
        })
        .catch(console.log)
    }

    componentDidMount(){
        // Consumir datos API
        this.loadData();
    }

    render() { 
        const{datosCargados, employers} = this.state;

        if(!datosCargados){return (<div>Cargando...</div>);}
        else{
            return (
                <div class="card">
                    <div class="card-header">
                        <Link type="button" className="btn btn-success" to={"/create"}>Agregar Empleado</Link>
                    </div>
                    <div class="card-body">
                    <h4>Empleados</h4>
                    <table className="table">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Nombre</th>
                                <th>Correo</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                employers.map(
                                    (empleado)=>(
                                            <tr key={empleado.id}>
                                            <td>{empleado.id}</td>
                                            <td>{empleado.name}</td>
                                            <td>{empleado.email}</td>
                                            <td>
                                                <div className="btn-group" role="group" aria-label="">
                                                    <Link type="button" className="btn btn-warning" to={"/update"}>Editar</Link>
                                                    <button type="button" className="btn btn-danger">Borrar</button>
                                                </div>
                                            </td>
                                        </tr>
                                    )
                                )
                            }
                        </tbody>
                    </table>
                    </div> 
                </div>
            );
        }
    }
}
export default List;