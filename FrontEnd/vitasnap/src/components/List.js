import React from 'react';
import {Link} from "react-router-dom";

class List extends React.Component {
    constructor(props) {
        super(props);
        this.state = { 
            datosCargados:false,
            users:[]
        }
    }
    state = {  }

    loadData() {
        fetch("/api/users", {
            method: 'GET',
            mode: 'cors',
            headers: {
                "X-API-KEY": "Baeldung"
            }
        })
        .then(response => response.json())
        .then((data) => {
            console.log("Data received from API:", data); // Log the received data
    
            // Check if _embedded object exists and contains user data
            const users = data._embedded && data._embedded.userList ? data._embedded.userList : [];
    
            this.setState({ datosCargados: true, users: users });
        })
        .catch(error => {
            console.error("Error fetching data:", error);
        });
    }
    

    componentDidMount(){
        // Consumir datos API
        this.loadData();
    }

    render() { 
        const{datosCargados, users} = this.state;

        if(!datosCargados){return (<div>Cargando...</div>);}
        else{
            return (
                <div class="card">
                    <div class="card-header">
                        <Link type="button" className="btn btn-success" to={"/create"}>Agregar Usuario</Link>
                    </div>
                    <div class="card-body">
                    <h4>Usuarios</h4>
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
                                users.map(
                                    (user)=>(
                                            <tr key={user.id}>
                                            <td>{user.id}</td>
                                            <td>{user.username}</td>
                                            <td>{user.email}</td>
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