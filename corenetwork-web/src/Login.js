import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';

const Login = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const handleLogin = async (event) => {
        event.preventDefault();

        try {
            const response = await axios.post('http://userhub:8087/auth/signin', {
                email,
                password
            });

            const { success, token } = response.data;

            if (success) {
                console.log('Login successful:', response.data);
                localStorage.setItem('token', token);
                navigate('/dashboard');
            } else {
                toast.error('Invalid email or password', {
                    position: toast.POSITION.TOP_CENTER
                });
            }
        } catch (error) {
            console.error('Login failed:', error);
            toast.error('An error occurred during login', {
                position: toast.POSITION.TOP_CENTER
            });
        }
    };

    return (
        <div className="login-container">
            <h2>Login</h2>
            <form onSubmit={handleLogin}>
                <div className="form-group">
                    <label>Email:</label>
                    <input
                        type="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                    />
                </div>
                <div className="form-group">
                    <label>Password:</label>
                    <input
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                </div>
                <button type="submit">Login</button>
            </form>
        </div>
    );
};

export default Login;
