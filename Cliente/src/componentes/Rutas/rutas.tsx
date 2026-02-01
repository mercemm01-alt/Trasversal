import { Navigate } from "react-router-dom";
import React from "react";

interface Props {
    children: React.ReactNode;
    soloAdmin?: boolean;
    }

    function RutasSeguras({ children, soloAdmin = false }: Props) {
        const admin = localStorage.getItem("admin");
        const usuario = localStorage.getItem("usuario");

        if (!admin || !usuario) {
            return <Navigate to="/login" />;
        }

        if (soloAdmin && admin !== "S") {
            return <Navigate to="/empleado" />;
        }

        return children;
}

export default RutasSeguras;
