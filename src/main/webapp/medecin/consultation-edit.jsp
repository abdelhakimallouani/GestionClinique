<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>

<html lang="fr">

<head>

```
<meta charset="UTF-8">

<title>Consultation médicale</title>

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

    .patient-section {
        background-color: #f8fafc;
        padding: 25px;
        border-radius: 10px;
        margin-bottom: 30px;
    }

    .patient-info {
        display: grid;
        grid-template-columns: repeat(2, 1fr);
        gap: 15px;
    }

    .info-card {
        background-color: white;
        padding: 15px;
        border-radius: 8px;
        border: 1px solid #e5e7eb;
    }

    .info-label {
        display: block;
        font-size: 13px;
        color: #6b7280;
        margin-bottom: 5px;
    }

    .info-value {
        font-size: 16px;
        font-weight: bold;
        color: #111827;
    }

    .consultation-section {
        padding: 25px;
        border: 1px solid #e5e7eb;
        border-radius: 10px;
    }

    .form-group {
        margin-bottom: 20px;
    }

    label {
        display: block;
        margin-bottom: 8px;
        font-weight: bold;
        color: #374151;
    }

    textarea {
        width: 100%;
        min-height: 120px;
        padding: 12px;
        border: 1px solid #d1d5db;
        border-radius: 7px;
        font-family: Arial, sans-serif;
        font-size: 14px;
        resize: vertical;
        outline: none;
    }

    textarea:focus {
        border-color: #2563eb;
        box-shadow: 0 0 0 3px rgba(37, 99, 235, 0.1);
    }

    .cost {
        margin: 20px 0;
        padding: 15px;
        background-color: #f0fdf4;
        border: 1px solid #bbf7d0;
        border-radius: 7px;
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
        border: none;
        border-radius: 7px;
        font-size: 14px;
        font-weight: bold;
        cursor: pointer;
        text-decoration: none;
        transition: 0.2s;
    }

    .btn-close {
        background-color: #2563eb;
        color: white;
    }

    .btn-close:hover {
        background-color: #1d4ed8;
    }

    .btn-cancel {
        background-color: #e5e7eb;
        color: #374151;
    }

    .btn-cancel:hover {
        background-color: #d1d5db;
    }

    @media (max-width: 700px) {

        body {
            padding: 20px 10px;
        }

        .container {
            padding: 20px;
        }

        .patient-info {
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
```

</head>

<body>

<div class="container">

```
<h1>Consultation médicale</h1>


<!-- ================= PATIENT ================= -->

<div class="patient-section">

    <h2>Informations du patient</h2>

    <div class="patient-info">

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

            <span class="info-label">Tension artérielle</span>

            <span class="info-value">
                ${patient.tensionArterielle}
            </span>

        </div>


        <div class="info-card">

            <span class="info-label">Fréquence cardiaque</span>

            <span class="info-value">
                ${patient.frequenceCardiaque}
            </span>

        </div>


        <div class="info-card">

            <span class="info-label">Température</span>

            <span class="info-value">
                ${patient.temperature}
            </span>

        </div>


        <div class="info-card">

            <span class="info-label">Fréquence respiratoire</span>

            <span class="info-value">
                ${patient.frequenceRespiratoire}
            </span>

        </div>

    </div>

</div>


<!-- ================= CONSULTATION ================= -->

<div class="consultation-section">

    <h2>Consultation</h2>


    <form
            method="post"
            action="${pageContext.request.contextPath}/medecin/consultation/edit">

        <input
                type="hidden"
                name="id"
                value="${consultation.id}">


        <div class="form-group">

            <label for="motif">
                Motif
            </label>

            <textarea
                    id="motif"
                    name="motif"
                    placeholder="Saisir le motif de la consultation..."
                    required>${consultation.motif}</textarea>

        </div>


        <div class="form-group">

            <label for="observations">
                Observations
            </label>

            <textarea
                    id="observations"
                    name="observations"
                    placeholder="Saisir les observations du médecin..."
                    required>${consultation.observations}</textarea>

        </div>


        <div class="form-group">

            <label for="diagnostic">
                Diagnostic
            </label>

            <textarea
                    id="diagnostic"
                    name="diagnostic"
                    placeholder="Saisir le diagnostic..."
                    required>${consultation.diagnostic}</textarea>

        </div>


        <div class="form-group">

            <label for="traitement">
                Traitement
            </label>

            <textarea
                    id="traitement"
                    name="traitement"
                    placeholder="Saisir le traitement proposé..."
                    required>${consultation.traitement}</textarea>

        </div>


        <div class="cost">

            <strong>Coût de la consultation :</strong>
            150.00 DH

        </div>


        <div class="actions">

            <button
                    type="submit"
                    class="btn btn-close">

                Clôturer la consultation

            </button>


            <a
                    class="btn btn-cancel"
                    href="${pageContext.request.contextPath}/medecin/consultation?id=${consultation.id}">

                Annuler

            </a>

        </div>

    </form>

</div>
```

</div>

</body>

</html>
