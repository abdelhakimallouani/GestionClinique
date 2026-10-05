<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html lang="fr">

<head>

    <meta charset="UTF-8">

    <title>Détails du patient</title>

    <style>

        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
            padding: 40px 20px;
            font-family: Arial, sans-serif;
            background-color: #f4f7fb;
            color: #333;
        }

        .container {
            max-width: 900px;
            margin: 0 auto;
            background-color: #ffffff;
            padding: 35px;
            border-radius: 12px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.08);
        }

        h1 {
            margin-top: 0;
            margin-bottom: 30px;
            text-align: center;
            color: #1f2937;
        }

        h2 {
            margin-top: 0;
            margin-bottom: 20px;
            color: #2563eb;
            font-size: 21px;
        }

        .section {
            margin-bottom: 30px;
            padding: 25px;
            border-radius: 10px;
        }

        .patient-section {
            background-color: #f8fafc;
        }

        .consultation-section {
            border: 1px solid #e5e7eb;
            background-color: #ffffff;
        }

        .info-grid {
            display: grid;
            grid-template-columns: repeat(2, 1fr);
            gap: 15px;
        }

        .info-card {
            background-color: #ffffff;
            padding: 15px;
            border-radius: 8px;
            border: 1px solid #e5e7eb;
        }

        .info-label {
            display: block;
            font-size: 13px;
            color: #6b7280;
            margin-bottom: 6px;
        }

        .info-value {
            font-size: 16px;
            font-weight: bold;
            color: #111827;
        }

        .status {
            display: inline-block;
            padding: 6px 12px;
            border-radius: 20px;
            background-color: #fef3c7;
            color: #92400e;
            font-size: 13px;
            font-weight: bold;
        }

        .consultation-info {
            display: flex;
            flex-direction: column;
            gap: 15px;
        }

        .consultation-item {
            padding: 15px;
            background-color: #f8fafc;
            border-radius: 8px;
            border: 1px solid #e5e7eb;
        }

        .consultation-label {
            display: block;
            margin-bottom: 6px;
            font-size: 13px;
            color: #6b7280;
            font-weight: bold;
        }

        .consultation-value {
            color: #374151;
            line-height: 1.5;
            white-space: pre-wrap;
        }

        .cost {
            padding: 15px;
            background-color: #f0fdf4;
            border: 1px solid #bbf7d0;
            border-radius: 8px;
            color: #166534;
        }

        .actions {
            display: flex;
            gap: 12px;
            margin-top: 25px;
        }

        .btn {
            display: inline-block;
            padding: 12px 20px;
            border-radius: 7px;
            font-size: 14px;
            font-weight: bold;
            text-decoration: none;
            transition: 0.2s;
        }

        .btn-consultation {
            background-color: #2563eb;
            color: white;
        }

        .btn-consultation:hover {
            background-color: #1d4ed8;
        }

        .btn-back {
            background-color: #e5e7eb;
            color: #374151;
        }

        .btn-back:hover {
            background-color: #d1d5db;
        }

        @media (max-width: 700px) {

            body {
                padding: 20px 10px;
            }

            .container {
                padding: 20px;
            }

            .info-grid {
                grid-template-columns: 1fr;
            }

            .actions {
                flex-direction: column;
            }

            .btn {
                text-align: center;
            }

        }

    </style>

</head>

<body>

<div class="container">

    <h1>Détails du patient</h1>


    <!-- ============================= -->
    <!-- INFORMATIONS PATIENT -->
    <!-- ============================= -->

    <div class="section patient-section">

        <h2>Informations du patient</h2>

        <div class="info-grid">

            <div class="info-card">
                <span class="info-label">Nom</span>
                <span class="info-value">
                    ${patient.nom}
                </span>
            </div>

            <div class="info-card">
                <span class="info-label">Prénom</span>
                <span class="info-value">
                    ${patient.prenom}
                </span>
            </div>

            <div class="info-card">
                <span class="info-label">Date de naissance</span>
                <span class="info-value">
                    ${patient.dateNaissance}
                </span>
            </div>

            <div class="info-card">
                <span class="info-label">N° sécurité sociale</span>
                <span class="info-value">
                    ${patient.numeroSecuriteSociale}
                </span>
            </div>

        </div>

    </div>


    <!-- ============================= -->
    <!-- SIGNES VITAUX -->
    <!-- ============================= -->

    <div class="section patient-section">

        <h2>Signes vitaux</h2>

        <div class="info-grid">

            <div class="info-card">

                <span class="info-label">
                    Tension artérielle
                </span>

                <span class="info-value">
                    ${patient.tensionArterielle}
                </span>

            </div>


            <div class="info-card">

                <span class="info-label">
                    Fréquence cardiaque
                </span>

                <span class="info-value">
                    ${patient.frequenceCardiaque}
                </span>

            </div>


            <div class="info-card">

                <span class="info-label">
                    Température
                </span>

                <span class="info-value">
                    ${patient.temperature}
                </span>

            </div>


            <div class="info-card">

                <span class="info-label">
                    Fréquence respiratoire
                </span>

                <span class="info-value">
                    ${patient.frequenceRespiratoire}
                </span>

            </div>

        </div>

    </div>


    <!-- ============================= -->
    <!-- CONSULTATION -->
    <!-- ============================= -->

    <div class="section consultation-section">

        <h2>Consultation</h2>

        <div class="consultation-info">


            <div class="consultation-item">

                <span class="consultation-label">
                    Statut
                </span>

                <span class="status">
                    ${consultation.statut}
                </span>

            </div>


            <div class="consultation-item">

                <span class="consultation-label">
                    Motif
                </span>

                <div class="consultation-value">
                    ${consultation.motif}
                </div>

            </div>


            <div class="consultation-item">

                <span class="consultation-label">
                    Observations
                </span>

                <div class="consultation-value">
                    ${consultation.observations}
                </div>

            </div>


            <div class="consultation-item">

                <span class="consultation-label">
                    Diagnostic
                </span>

                <div class="consultation-value">
                    ${consultation.diagnostic}
                </div>

            </div>


            <div class="consultation-item">

                <span class="consultation-label">
                    Traitement
                </span>

                <div class="consultation-value">
                    ${consultation.traitement}
                </div>

            </div>


            <div class="cost">

                <strong>Coût :</strong>

                ${consultation.cout} DH

            </div>


            <div class="consultation-item">

                <span class="consultation-label">
                    Date de consultation
                </span>

                <div class="consultation-value">
                    ${consultation.dateConsultation}
                </div>

            </div>

        </div>


        <!-- ============================= -->
        <!-- ACTIONS -->
        <!-- ============================= -->

        <div class="actions">

            <a
                    class="btn btn-consultation"
                    href="${pageContext.request.contextPath}/medecin/consultation/edit?id=${consultation.id}">
                Faire la consultation
            </a>

            <a
                    class="btn btn-back"
                    href="${pageContext.request.contextPath}/medecin/patients">
                Retour aux patients
            </a>

        </div>

    </div>

</div>

</body>

</html>