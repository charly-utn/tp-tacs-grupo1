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
    const [responseMessage, setResponseMessage] = useState('');
    const [responseMessageType, setResponseMessageType] = useState('');

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
        setResponseMessage('Inicio de sesión exitoso');
        setResponseMessageType('success');
        console.log('Respuesta del servidor:', response);
      } catch (error) {
        setResponseMessage('Error al iniciar sesión. Por favor, inténtalo de nuevo.');
        setResponseMessageType('error');
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
          {responseMessage && (
              <div className={`response-message ${responseMessageType}`}>
                  {responseMessage}
              </div>
          )}
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