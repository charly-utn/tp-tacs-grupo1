import { useEffect, useState } from 'react';
import { Link, NavLink, useNavigate } from 'react-router-dom';
import { User } from '../../interfaces/User';
import { logoutUser, user } from '../../services/UsersApiClient'

export const Navbar = () => {

    const navigate = useNavigate();

    const [ loggedUser, setLoggedUser ] = useState<User>(user.user)
    
    useEffect(() => {
        const updateLoggedUser = (newUser: User) => {
            setLoggedUser(newUser);
        }
        user.onUserChanges = updateLoggedUser;
    },[])

    const onLogout = () => {
        navigate('/login', {
            replace: true
        });
        logoutUser();
    }

    return (
        <nav className="navbar navbar-expand-sm navbar-dark bg-dark p-2">
            
            <Link 
                className="navbar-brand" 
                to="/"
            >
                Home
            </Link>

            <div className="navbar-collapse">
                <div className="navbar-nav">

                    <NavLink 
                        className={ ({isActive}) => `nav-item nav-link  ${ isActive ? 'active':'' }` }
                        to="/products"
                    >
                        Productos
                    </NavLink>

                    <NavLink 
                        className={ ({isActive}) => `nav-item nav-link  ${ isActive ? 'active':'' }` }
                        to="/orders"
                    >
                        Pedidos
                    </NavLink>
                    

                </div>
            </div>

            <div className="navbar-collapse collapse w-100 order-3 dual-collapse2 d-flex justify-content-end">
                <ul className="navbar-nav ml-auto">
                   
                    <span className="nav-item nav-link text-primary">
                        {loggedUser.userName}
                    </span>

                    <button
                        className="nav-item nav-link btn"
                        onClick={ onLogout }
                    >
                        {user.user.userName ? 'Logout'  : 'Login'}
                    </button>

                </ul>
            </div>
        </nav>
    )
}