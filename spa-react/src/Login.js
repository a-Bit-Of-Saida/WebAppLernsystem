import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import "./Login.css"; // Import the CSS file for styling

function Login() {
    const [error, setError] = useState(""); //State to store error messages
    const [email, setEmail] = useState(""); //State to store the email input
    const [password, setPassword] = useState(""); //State to store the password input
    const navigate = useNavigate(); //Hook for navigating to different routes programmatically

    useEffect(() => {
        // Blocks the back button in the browser
        const handleBackButton = () => {
          window.history.pushState(null, "", window.location.href);
        };
    
        window.history.pushState(null, "", window.location.href);
        window.addEventListener("popstate", handleBackButton);
    
        return () => {
          window.removeEventListener("popstate", handleBackButton);
        };
      }, []);
    
      const handleSubmit = async (event) => {
        event.preventDefault(); // Prevents the page from reloading on form submission
        try {
          const response = await axios.post("http://localhost:8085/auth/login", {
            email,
            password,
          });
          if (response.status === 200) {
            console.log("Login successful:", response.data);
            alert("Login successful!");
            const userId = response.data.userId;
    
            if (userId) {
              localStorage.setItem("userId", response.data.userId.toString());
              // 🚀 Redirect to the dashboard without the ability to go back
          navigate(`/dashboard/${userId}`, { replace: true });
        } else {
          setError("User-ID not found");
        }
      } else {
        setError("Login failed");
      }
    } catch (error) {
      console.error("Error during login:", error);
      setError("Error during login: " + error.message);
    }
  };

  return (
    <div className="login-container">
      <button onClick={() => navigate("/")} className="home-button">Home</button>
      <h2>Bitte loggen Sie sich ein:</h2>
      <form onSubmit={handleSubmit} className="login-form">
        <label>
          E-Mail:
          <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} required />
        </label>
        <label>
          Passwort:
          <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} required />
        </label>
        <button type="submit" className="login-button">Login</button>
        {error && <p className="error-message">{error}</p>}
      </form>
    </div>
  );
}

export default Login;