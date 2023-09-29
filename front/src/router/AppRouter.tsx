import React from 'react';
import {Navigate, Route, Routes} from 'react-router-dom';
import {Home, Login, Register} from '../auth/pages';
import {Orders} from '../auth/pages/Orders';
import {Navbar} from '../components/navbar/Navbar';
import { Products } from '../views'

class ProtectedRoute extends React.Component<{ element: any }> {
    render() {
        let {element} = this.props;
        console.log('elem: ', element );
        console.log('token: ', localStorage.getItem('token'));
        if (localStorage.getItem('token') !== null) {
            return element;
        } else {
            return <Navigate to="/login"/>;
        }
    }
}

export const AppRouter = () => {
    var isAuthenticated = localStorage.getItem('token') !== null;
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
                        element={<ProtectedRoute element={<Home />} />}
                    />
                    <Route
                        path="/orders"
                        element={<ProtectedRoute element={<Orders />} />}
                    />
                  
                    <Route 
                      path="/productos" 
                      element={<ProtectedRoute element={<Products />} />}
                     />

                    {/* Redirección desde la raíz ("/") a la página de inicio ("/home") */}
                    <Route path="/*" element={<Navigate to="/home" />} />
                </Routes>
            </div>
        </>
    );
};

