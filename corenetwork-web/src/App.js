import './App.css';
import React from 'react';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import Login from './Login';
import Dashboard from './Dashboard';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import PrivateRoute from "./PrivateRoute";

function App() {
    return (
        <Router>
            <div className="App">
                <ToastContainer />
                <Routes>
                    <Route path="/" element={<Login />} />
                    <Route
                        path="/dashboard"
                        element={<PrivateRoute component={Dashboard} />}
                    />
                </Routes>
            </div>
        </Router>
    );
}

export default App;
