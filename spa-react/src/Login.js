import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import "./Login.css"; // Import the CSS file for styling

function Login() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
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