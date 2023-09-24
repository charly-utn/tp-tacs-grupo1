import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { loginUser } from '../../services/UsersApiClient';
import './login.css';


export const Login = () => {
    const navigate = useNavigate();
  
    const [formData, setFormData] = useState({
      userName: '',
      password: '',
    });
  
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
        const response = await loginUser({
          userName: formData.userName,
          password: formData.password,
        });
        localStorage.setItem('token', response.token);
        console.log('Respuesta del servidor:', response);
      } catch (error) {
        console.error('Error al iniciar sesión:', error);
      }
    };
  
    const registrarse = async (e: any) => {
      
    };
  
    const redirectToRegister = () => {
      navigate(`/register`);
    };
  
  
    return (
      <div className="login-container">
        <h2>Iniciar Sesión</h2>
        <form onSubmit={iniciarSesion}>
          <div className="form-group">
            <label htmlFor="userName">Nombre de Usuario:</label>
            <input
              type="text"
              id="userName"
              name="userName"
              value={formData.userName}
              onChange={handleChange}
              required
            />
          </div>
          <div className="form-group">
            <label htmlFor="password">Contraseña:</label>
            <input
              type="password"
              id="password"
              name="password"
              value={formData.password}
              onChange={handleChange}
              required
            />
          </div>
          <button type="submit">Iniciar Sesión</button>
        </form>
        <div className="register-link">
          <p>¿No tienes una cuenta?</p>
          <button onClick={redirectToRegister}>Registrarse</button>
        </div>
      </div>
    );
}