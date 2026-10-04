<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html lang="fr">

<head>

    <meta charset="UTF-8">

    <title>Consultation médicale</title>

</head>

<body>

<h1>Consultation médicale</h1>

<h2>Patient</h2>

<p>
    <strong>Nom :</strong>
    ${patient.nom}
</p>

<p>
    <strong>Prénom :</strong>
    ${patient.prenom}
</p>

<p>
    <strong>Tension :</strong>
    ${patient.tensionArterielle}
</p>

<p>
    <strong>Fréquence cardiaque :</strong>
    ${patient.frequenceCardiaque}
</p>

<p>
    <strong>Température :</strong>
    ${patient.temperature}
</p>

<p>
    <strong>Fréquence respiratoire :</strong>
    ${patient.frequenceRespiratoire}
</p>


<hr>


<h2>Consultation</h2>

<form
        method="post"
        action="${pageContext.request.contextPath}/medecin/consultation/edit">

    <input
            type="hidden"
            name="id"
            value="${consultation.id}">


    <div>

        <label>Motif</label>

        <br>

        <textarea
                name="motif"
                rows="4"
                cols="60"
                required>${consultation.motif}</textarea>

    </div>

    <br>


    <div>

        <label>Observations</label>

        <br>

        <textarea
                name="observations"
                rows="4"
                cols="60"
                required>${consultation.observations}</textarea>

    </div>

    <br>


    <div>

        <label>Diagnostic</label>

        <br>

        <textarea
                name="diagnostic"
                rows="4"
                cols="60"
                required>${consultation.diagnostic}</textarea>

    </div>

    <br>


    <div>

        <label>Traitement</label>

        <br>

        <textarea
                name="traitement"
                rows="4"
                cols="60"
                required>${consultation.traitement}</textarea>

    </div>

    <br>

    <p>
        <strong>Coût :</strong> 150.00 DH
    </p>

    <button type="submit">
        Clôturer la consultation
    </button>

</form>


<br>

<a href="${pageContext.request.contextPath}/medecin/consultation?id=${consultation.id}">
    Annuler
</a>

</body>

</html>