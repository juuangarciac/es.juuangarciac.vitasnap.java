import React from 'react'
import {BrowserRouter as Router, Routes, Route, Link} from "react-router-dom";
import { Managment } from '../components/js/managment/Managment';
import { ManagmentUser } from '../components/js/managment/user/ManagmentUser';
import { Home } from '../pages/Home';

export const MainRoute = () => {
  return (
    <div className='main-route'>
        {/*Configuracion de las rutas de la aplicacion*/}
        <Router>
            <Routes>
              <Route path='/' element={<Home />} >
                  <Route path='managment/*' element={<Managment />}>
                      <Route path='user' element={<ManagmentUser />} />
                  </Route>
              </Route>
            </Routes>
        </Router>
    </div>
  )
}
