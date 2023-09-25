import React from 'react';
import { Route, Routes, Navigate } from 'react-router-dom';
import { Login, Register } from '../auth/pages';
import {  Home } from '../auth/pages/Home';
import { Navbar } from '../components/navbar/Navbar';


class ProtectedRoute extends React.Component<{ element: any, authenticated: any }> {
    render() {
        let {element, authenticated} = this.props;
        if (authenticated) {
            return element;
        } else {
            return <Navigate to="/login"/>;
        }
    }
}

export const AppRouter = () => {
    const isAuthenticated = localStorage.getItem('token') !== null;
    console.log('isAuthenticated:', isAuthenticated);
    return (
        <>
            <Navbar />

            <div className="containers">
                <Routes>
                    {/* Ruta pública */}
                    <Route path="/login" element={<Login />} />
                    <Route path="/register" element={<Register />} />

                    {/* Ruta protegida (requiere autenticación) */}
                    <Route
                        path="/home"
                        element={<ProtectedRoute element={<Home />} authenticated={isAuthenticated} />}
                    />

                    {/* Redirección desde la raíz ("/") a la página de inicio ("/home") */}
                    <Route path="/*" element={<Navigate to="/home" />} />
                </Routes>
            </div>
        </>
    );
};

