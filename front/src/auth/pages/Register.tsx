import { useState } from "react";
import { createUser } from "../../services/UsersApiClient";
import { CreateUser } from "../../interfaces/CreateUser";
import './register.css';
import { useNavigate } from 'react-router-dom';

export const Register = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [email, setEmail] = useState('');
    const [message, setMessage] = useState('');

    const navigate = useNavigate();
  
    const handleSubmit = async (event: any) => {
      event.preventDefault();
      const user: CreateUser = { userName: username, password, email };
      try {
        const response = await createUser(user);
        setMessage(`User ${response.username} created successfully!`);
        alert("Registro correcto");
        navigate('/login');
      } catch (error) {
        setMessage('Error creating user. Please try again.');
      }
    };
    return (
      <div className="form-container">
        <form onSubmit={handleSubmit}> 
          <label>
            Username:
            <input type="text" value={username} onChange={(e) => setUsername(e.target.value)} />
          </label>
          <label>
            Password:
            <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
          </label>
          <label>
            Email:
            <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} />
          </label>
          <button type="submit">Register</button>
          {message && <p>{message}</p>}
        </form>
      </div>
    );
  };