<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, java.time.LocalDate, java.time.format.DateTimeFormatter, java.util.Locale, ma.youcode.clinique.entity.Patient" %>
<%!
    private String escapeHtml(Object value) {
        if (value == null) return "—";
        return value.toString().replace("&", "&amp;").replace("<", "&lt;")
                .replace(">", "&gt;").replace("\"", "&quot;").replace("'", "&#39;");
    }
%>
<%
    List<Patient> patients = (List<Patient>) request.getAttribute("patients");
    if (patients == null) patients = List.of();
    LocalDate dateSelectionnee = (LocalDate) request.getAttribute("dateSelectionnee");
    if (dateSelectionnee == null) dateSelectionnee = LocalDate.now();
    String erreurDate = (String) request.getAttribute("erreurDate");
    String triSelectionne = (String) request.getAttribute("triSelectionne");
    String ordreSelectionne = (String) request.getAttribute("ordreSelectionne");
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Patients par date - Clinique</title>
    <style>
        * { box-sizing: border-box; }
        body { margin: 0; padding: 32px; color: #24343a; background: #f2f7f8; font-family: Arial, sans-serif; }
        main { max-width: 1200px; margin: auto; }
        h1 { margin: 0 0 8px; color: #197c8c; }
        .subtitle { margin: 0 0 24px; color: #60747b; }
        .card { overflow: hidden; border-radius: 10px; background: white; box-shadow: 0 4px 16px rgba(30, 65, 75, .08); }
        .filter { display: flex; flex-wrap: wrap; align-items: end; gap: 12px; padding: 20px; border-bottom: 1px solid #e5edef; }
        label { display: grid; gap: 7px; color: #52676e; font-weight: bold; }
        input, select, button { min-height: 40px; padding: 8px 12px; border: 1px solid #cbd9dc; border-radius: 6px; font: inherit; }
        button { color: white; background: #197c8c; border: 0; cursor: pointer; font-weight: bold; }
        button:hover { background: #126574; }
        .summary { padding: 16px 20px; color: #52676e; }
        .table-wrap { overflow-x: auto; }
        table { width: 100%; border-collapse: collapse; text-align: left; }
        th, td { padding: 13px 16px; border-bottom: 1px solid #edf1f2; white-space: nowrap; }
        th { color: #52676e; background: #f8fbfb; font-size: 13px; }
        tbody tr:last-child td { border-bottom: 0; }
        .patient-name { font-weight: bold; }
        .empty { padding: 44px 20px; text-align: center; color: #60747b; }
        .empty strong { display: block; margin-bottom: 8px; color: #24343a; font-size: 18px; }
        .error { margin: 0 20px 16px; color: #a52626; }
        @media (max-width: 650px) { body { padding: 16px; } .filter { align-items: stretch; flex-direction: column; } }
    </style>
</head>
<body>
<main>
    <h1>Patients par date</h1>
    <p class="subtitle">Choisissez la date d'arrivée pour consulter les patients enregistrés.</p>
    <section class="card" aria-label="Patients filtrés par date">
        <form class="filter" method="get" action="${pageContext.request.contextPath}/medecin/patients">
            <label for="date">Date d'arrivée
                <input id="date" type="date" name="date" value="<%= dateSelectionnee %>" required>
            </label>
            <label for="tri">Trier par
                <select id="tri" name="tri">
                    <option value="arrivee" <%= "arrivee".equals(triSelectionne) ? "selected" : "" %>>Heure d'arrivée</option>
                    <option value="nom" <%= "nom".equals(triSelectionne) ? "selected" : "" %>>Nom et prénom</option>
                    <option value="naissance" <%= "naissance".equals(triSelectionne) ? "selected" : "" %>>Date de naissance</option>
                </select>
            </label>
            <label for="ordre">Ordre
                <select id="ordre" name="ordre">
                    <option value="asc" <%= "asc".equals(ordreSelectionne) ? "selected" : "" %>>Croissant</option>
                    <option value="desc" <%= "desc".equals(ordreSelectionne) ? "selected" : "" %>>Décroissant</option>
                </select>
            </label>
            <button type="submit">Appliquer</button>
        </form>
        <% if (erreurDate != null) { %><p class="error"><%= escapeHtml(erreurDate) %></p><% } %>
        <div class="summary"><strong><%= patients.size() %></strong> patient(s) arrivé(s) le <%= dateSelectionnee.format(dateFormatter) %></div>
        <% if (patients.isEmpty()) { %>
            <div class="empty"><strong>Aucun patient trouvé</strong><span>Aucun patient n'a été enregistré à cette date.</span></div>
        <% } else { %>
            <div class="table-wrap">
                <table>
                    <thead><tr>
                        <th>Patient</th><th>Numéro de sécurité sociale</th><th>Date de naissance</th>
                        <th>Heure d'arrivée</th><th>Tension</th><th>Fréquence cardiaque</th>
                        <th>Température</th><th>Fréquence respiratoire</th>
                    </tr></thead>
                    <tbody>
                    <% for (Patient patient : patients) { %>
                        <tr>
                            <td class="patient-name"><%= escapeHtml(patient.getNom()) %> <%= escapeHtml(patient.getPrenom()) %></td>
                            <td><%= escapeHtml(patient.getNumeroSecuriteSociale()) %></td>
                            <td><%= patient.getDateNaissance() == null ? "—" : escapeHtml(patient.getDateNaissance().format(dateFormatter)) %></td>
                            <td><%= patient.getHeureArrivee() == null ? "—" : escapeHtml(patient.getHeureArrivee().format(timeFormatter)) %></td>
                            <td><%= escapeHtml(patient.getTensionArterielle()) %></td>
                            <td><%= patient.getFrequenceCardiaque() %> bpm</td>
                            <td><%= String.format(Locale.FRANCE, "%.2f °C", patient.getTemperature()) %></td>
                            <td><%= patient.getFrequenceRespiratoire() %> /min</td>
                        </tr>
                    <% } %>
                    </tbody>
                </table>
            </div>
        <% } %>
    </section>
</main>
</body>
</html>
