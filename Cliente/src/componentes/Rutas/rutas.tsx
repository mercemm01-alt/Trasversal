import { Navigate } from "react-router-dom";
import React from "react";

interface Props {
    children: React.ReactNode;
    rolPermitido: string,
    }

    function RutasSeguras({ children, rolPermitido }: Props) {
        const rol = localStorage.getItem("rol");
        

        if (!rol){
            return <Navigate to="/inicio" replace />;
        }

        if (rol !== rolPermitido) {
            return <Navigate to="/login" replace />;
        }

        return children;
}

export default RutasSeguras;
