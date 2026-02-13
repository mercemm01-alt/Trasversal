import React, { useState, useEffect } from 'react';
import './CSS/BeneficiosStats.css';

const BeneficiosStats: React.FC = () => {
    const [beneficio, setBeneficio] = useState<number>(0);
    const [periodo, setPeriodo] = useState<string>('dia');

    const obtenerBeneficios = async (filtro: string) => {
        try {
            const response = await fetch(`/api/pedidos/beneficios?periodo=${filtro}`);
            if (response.ok) {
                const data = await response.json();
                setBeneficio(data.beneficio); 
                setPeriodo(filtro);
            }
        } catch (error) {
            console.error("Error obteniendo beneficios:", error);
        }
    };

    useEffect(() => {
        obtenerBeneficios('dia');
    }, []);

    return (
        <div className="stats-card">
            <div className="stats-header">
                <h3>Beneficio Neto</h3>
                <span className="badge">
                    {periodo === 'dia' ? 'Hoy' : periodo === 'semana' ? 'Esta Semana' : 'Este Mes'}
                </span>
            </div>

            <div className="stats-body">
                <h1 className="money-display">{beneficio ? beneficio.toFixed(2) : '0.00'} €</h1>
            </div>

            <div className="stats-actions">
                <button 
                    className={periodo === 'dia' ? 'active' : ''} 
                    onClick={() => obtenerBeneficios('dia')}
                >
                    Diario
                </button>
                <button 
                    className={periodo === 'semana' ? 'active' : ''} 
                    onClick={() => obtenerBeneficios('semana')}
                >
                    Semanal
                </button>
                <button 
                    className={periodo === 'mes' ? 'active' : ''} 
                    onClick={() => obtenerBeneficios('mes')}
                >
                    Mensual
                </button>
            </div>
        </div>
    );
};

export default BeneficiosStats;