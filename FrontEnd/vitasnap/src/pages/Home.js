import React from 'react'
import { Header } from '../components/js/layout/Header'
import { Footer } from '../components/js/layout/Footer'
import { Nav } from '../components/js/layout/Nav'
import { Outlet } from 'react-router-dom'
import './css/layout.css'

export const Home = () => {
  return (
    <div className='page'>
        <Header className="header"/>

        <Nav  className="nav"/>

        <body className='body'>
          <Outlet />
        </body>

        <Footer  className="footer"/>
    </div>
  )
}
