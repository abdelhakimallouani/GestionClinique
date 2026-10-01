<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Enregistrer un patient</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f2f7f8; margin: 0; padding: 30px; color: #24343a; }
        main { max-width: 700px; margin: auto; padding: 28px; background: white; border-radius: 10px; }
        h1 { color: #197c8c; margin-top: 0; }
        h2 { margin-top: 24px; font-size: 18px; }
        .grid { display: grid; grid-template-columns: 1fr 1fr; gap: 14px; }
        label { display: block; font-weight: bold; margin-bottom: 6px; }
        input { box-sizing: border-box; width: 100%; padding: 10px; border: 1px solid #cbd5d8; border-radius: 5px; }
        button { margin-top: 22px; padding: 11px 18px; border: 0; border-radius: 5px; color: white; background: #197c8c; cursor: pointer; }
        .error { padding: 10px; color: #8f1d1d; background: #ffe7e7; border-radius: 5px; }
        a { color: #197c8c; }
        @media (max-width: 600px) { body { padding: 12px; } main { padding: 18px; } .grid { grid-template-columns: 1fr; } }
    </style>
</head>
<body>
<main>
    <h1>Enregistrer un patient</h1>

    <% if (request.getAttribute("erreurs") != null) { %>
        <div class="error" role="alert">
            <strong>Corrige ces informations :</strong>
            <ul>
                <% for (String erreur : (java.util.List<String>) request.getAttribute("erreurs")) { %>
                    <li><%= erreur %></li>
                <% } %>
            </ul>
        </div>
    <% } %>

    <form method="post" action="${pageContext.request.contextPath}/infirmier/patients">
        <h2>Identité</h2>
        <div class="grid">
            <div>
                <label for="nom">Nom</label>
                <input id="nom" name="nom" type="text" maxlength="100" required>
            </div>
            <div>
                <label for="prenom">Prénom</label>
                <input id="prenom" name="prenom" type="text" maxlength="100" required>
            </div>
            <div>
                <label for="date_naissance">Date de naissance</label>
                <input id="date_naissance" name="date_naissance" type="date" max="<%= java.time.LocalDate.now() %>" required>
            </div>
            <div>
                <label for="numero_securite_sociale">Numéro de sécurité sociale</label>
                <input id="numero_securite_sociale" name="numero_securite_sociale" type="text" maxlength="50" required>
            </div>
        </div>

        <h2>Signes vitaux</h2>
        <div class="grid">
            <div>
                <label for="tension_arterielle">Tension artérielle</label>
                <input id="tension_arterielle" name="tension_arterielle" type="text" maxlength="7" pattern="[1-9][0-9]{0,2}/[1-9][0-9]{0,2}" title="Format attendu : 120/80" placeholder="120/80">
            </div>
            <div>
                <label for="frequence_cardiaque">Fréquence cardiaque (bpm)</label>
                <input id="frequence_cardiaque" name="frequence_cardiaque" type="number" min="1" step="1" required>
            </div>
            <div>
                <label for="temperature">Température (°C)</label>
                <input id="temperature" name="temperature" type="number" min="0.01" max="99.99" step="0.01" required>
            </div>
            <div>
                <label for="frequence_respiratoire">Fréquence respiratoire</label>
                <input id="frequence_respiratoire" name="frequence_respiratoire" type="number" min="1" step="1" required>
            </div>
        </div>

        <button type="submit">Enregistrer le patient</button>
    </form>
    <p><a href="${pageContext.request.contextPath}/infirmier/patients">Retour à la liste des patients</a></p>
</main>
</body>
</html>
