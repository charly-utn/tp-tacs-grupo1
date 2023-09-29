import {useContext, useState} from 'react';
import {NavLink, useNavigate} from 'react-router-dom';
import {loginUser} from '../../services/UsersApiClient';
import { AuthContext } from '../../context/AuthContext';
import {Credentials} from "../../interfaces/Credentials";

const initialLogin = {
  userName: '',
  password: '',
}

export const Login = () => {
    const navigate = useNavigate();

    const {handlerLogin} = useContext(AuthContext)
  
    const [formData, setFormData] = useState(initialLogin);

    const [responseMessage, setResponseMessage] = useState('');
    const [responseMessageType, setResponseMessageType] = useState('');

    const [errorLogin, setErrorLogin] = useState(false)

    const handleChange = (e: any) => {
      const { name, value } = e.target;
      setFormData({
        ...formData,
        [name]: value,
      });
    };
  
    const iniciarSesion = async (e: any) => {
      e.preventDefault();
      
      try {
        await handlerLogin({
          userName: formData.userName,
          password: formData.password
        })
        
        /*const response = await loginUser({
          userName: formData.userName,
          password: formData.password,
        });*/
        //localStorage.setItem('token', response.token);
        //setResponseMessage('Inicio de sesión exitoso');
        //setResponseMessageType('success');
        //console.log('Respuesta del servidor:', response);
        navigate("/home");
      } catch (error) {
        //setResponseMessage('Error al iniciar sesión. Por favor, inténtalo de nuevo.');
        //setResponseMessageType('error');
        //console.error('Error al iniciar sesión:', error);
        //Swal.fire('Error login', 'Username o password invalidos', 'error')
        setErrorLogin(true)
      }
      setFormData(initialLogin)
    };

    const registrarse = async (e: any) => {

    };

    return (
        <div className="container mt-5">
            <div className="row justify-content-center">
                <div className="col-md-6">
                    <div className={`alert alert-warning ${errorLogin ? 'd-block' : 'd-none'}`}>
                      Error al iniciar sesión. Por favor, inténtalo de nuevo.
                    </div>
                    <div className="card">
                        <div className="card-header">Iniciar Sesión</div>
                        <div className="card-body">
                            <form onSubmit={iniciarSesion}>
                                <div className="mb-3">
                                    <label htmlFor="userName" className="form-label">Nombre de Usuario:</label>
                                    <input
                                        type="text"
                                        id="userName"
                                        name="userName"
                                        value={formData.userName}
                                        onChange={handleChange}
                                        className="form-control"
                                        required
                                    />
                                </div>
                                <div className="mb-3">
                                    <label htmlFor="password" className="form-label">Contraseña:</label>
                                    <input
                                        type="password"
                                        id="password"
                                        name="password"
                                        value={formData.password}
                                        onChange={handleChange}
                                        className="form-control"
                                        required
                                    />
                                </div>
                                <div className="d-grid">
                                    <button type="submit" className="btn btn-primary">Iniciar Sesión</button>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div className="mt-3 text-center">
                        <p>¿No tienes una cuenta?</p>
                        <NavLink className="btn btn-link" to="/register">Registrarse</NavLink>
                    </div>
                </div>
            </div>
        </div>
    );
};