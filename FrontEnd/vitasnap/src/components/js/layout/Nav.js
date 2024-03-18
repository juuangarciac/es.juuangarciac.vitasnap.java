import React from 'react'
import { NavLink } from 'react-router-dom'
import '../../css/nav.css'

export const Nav = () => {
  return (
    <div className='nav'>
        <ul>
          <li>
            <NavLink to={'/#'}>Inicio</NavLink>
          </li>
          <li>
            <NavLink to={'/#'}>Mi Perfil</NavLink>
          </li>
          <li>
            <NavLink to={'/#'}>Sobre Nosotros</NavLink>
          </li>
        </ul>
    </div>
  )
}
