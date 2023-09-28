import {useState} from 'react';
import {useNavigate} from 'react-router-dom';
import {loginUser} from '../../services/UsersApiClient';


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
        navigate("/Home");
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
        <div className="container mt-5">
            <div className="row justify-content-center">
                <div className="col-md-6">
                    <div className={`alert ${responseMessageType} ${responseMessage ? 'd-block' : 'd-none'}`}>
                        {responseMessage}
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
                        <button onClick={redirectToRegister} className="btn btn-link">Registrarse</button>
                    </div>
                </div>
            </div>
        </div>
    );
};