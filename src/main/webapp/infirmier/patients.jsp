<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page
        import="java.util.List, java.time.format.DateTimeFormatter, java.time.LocalDate, java.time.LocalDateTime, java.util.Locale, ma.youcode.clinique.entity.Patient"
        %>
        <%! private String escapeHtml(Object value) { if (value==null) return "—" ; return value.toString()
            .replace("&", "&amp;" ) .replace("<", "&lt;" ) .replace(">", "&gt;")
            .replace("\"", "&quot;")
            .replace("'", "&#39;");
            }
            %>
            <% List<Patient> patients = (List<Patient>) request.getAttribute("patients");
                    if (patients == null) patients = List.of();
                    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
                    String dateDuJour = LocalDate.now().format(dateFormatter);
                    %>
                    <!DOCTYPE html>
                    <html lang="fr">

                    <head>
                        <meta charset="UTF-8">
                        <meta name="viewport" content="width=device-width, initial-scale=1">
                        <title>Patients du jour - Clinique</title>
                        <style>
                            * {
                                box-sizing: border-box;
                            }

                            body {
                                margin: 0;
                                padding: 32px;
                                color: #24343a;
                                background: #f2f7f8;
                                font-family: Arial, sans-serif;
                            }

                            main {
                                max-width: 1200px;
                                margin: auto;
                            }

                            .header {
                                display: flex;
                                align-items: center;
                                justify-content: space-between;
                                gap: 16px;
                                margin-bottom: 24px;
                            }

                            h1 {
                                margin: 0 0 8px;
                                color: #197c8c;
                            }

                            .subtitle {
                                margin: 0;
                                color: #60747b;
                            }

                            .button {
                                display: inline-block;
                                padding: 11px 16px;
                                border-radius: 6px;
                                color: white;
                                background: #197c8c;
                                text-decoration: none;
                                font-weight: bold;
                            }

                            .button:hover {
                                background: #126574;
                            }

                            .card {
                                overflow: hidden;
                                border-radius: 10px;
                                background: white;
                                box-shadow: 0 4px 16px rgba(30, 65, 75, .08);
                            }

                            .summary {
                                padding: 16px 20px;
                                border-bottom: 1px solid #e5edef;
                                color: #52676e;
                            }

                            .table-wrap {
                                overflow-x: auto;
                            }

                            table {
                                width: 100%;
                                border-collapse: collapse;
                                text-align: left;
                            }

                            th,
                            td {
                                padding: 13px 16px;
                                border-bottom: 1px solid #edf1f2;
                                white-space: nowrap;
                            }

                            th {
                                color: #52676e;
                                background: #f8fbfb;
                                font-size: 13px;
                            }

                            tbody tr:last-child td {
                                border-bottom: 0;
                            }

                            .patient-name {
                                font-weight: bold;
                                color: #24343a;
                            }

                            .empty {
                                padding: 48px 20px;
                                text-align: center;
                                color: #60747b;
                            }

                            .empty strong {
                                display: block;
                                margin-bottom: 8px;
                                color: #24343a;
                                font-size: 18px;
                            }

                            .logout {
                                display: inline-block;
                                padding: 10px 16px;
                                margin-bottom: 20px;
                                color: white;
                                background-color: #dc2626;
                                border-radius: 7px;
                                text-decoration: none;
                                font-weight: bold;
                            }

                            .logout:hover {
                                background-color: #b91c1c;
                            }

                            @media (max-width: 650px) {
                                body {
                                    padding: 16px;
                                }

                                .header {
                                    align-items: flex-start;
                                    flex-direction: column;
                                }
                            }
                        </style>
                    </head>

                    <body>
                        <a class="logout" href="${pageContext.request.contextPath}/logout">
                            Déconnexion
                        </a>
                        <main>
                            <header class="header">
                                <div>
                                    <h1>Patients du jour</h1>
                                    <p class="subtitle">Patients enregistrés le <%= escapeHtml(dateDuJour) %>
                                    </p>
                                </div>
                                <a class="button"
                                    href="${pageContext.request.contextPath}/infirmier/patients?action=nouveau">+
                                    Ajouter un patient</a>

                            </header>

                            <section class="card" aria-label="Liste des patients du jour">
                                <div class="summary"><strong>
                                        <%= patients.size() %>
                                    </strong> patient(s) aujourd'hui</div>
                                <% if (patients.isEmpty()) { %>
                                    <div class="empty">
                                        <strong>Aucun patient enregistré aujourd'hui</strong>
                                        <span>Les nouveaux patients apparaîtront ici après leur enregistrement.</span>
                                    </div>
                                    <% } else { %>
                                        <div class="table-wrap">
                                            <table>
                                                <thead>
                                                    <tr>
                                                        <th>Patient</th>
                                                        <th>Numéro de sécurité sociale</th>
                                                        <th>Date de naissance</th>
                                                        <th>Heure d'arrivée</th>
                                                        <th>Tension</th>
                                                        <th>Fréquence cardiaque</th>
                                                        <th>Température</th>
                                                        <th>Fréquence respiratoire</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <% for (Patient patient : patients) { %>
                                                        <tr>
                                                            <td class="patient-name">
                                                                <%= escapeHtml(patient.getNom()) %>
                                                                    <%= escapeHtml(patient.getPrenom()) %>
                                                            </td>
                                                            <td>
                                                                <%= escapeHtml(patient.getNumeroSecuriteSociale()) %>
                                                            </td>
                                                            <td>
                                                                <%= patient.getDateNaissance()==null ? "—" :
                                                                    escapeHtml(patient.getDateNaissance().format(dateFormatter))
                                                                    %>
                                                            </td>
                                                            <td>
                                                                <%= patient.getHeureArrivee()==null ? "—" :
                                                                    escapeHtml(patient.getHeureArrivee().format(timeFormatter))
                                                                    %>
                                                            </td>
                                                            <td>
                                                                <%= escapeHtml(patient.getTensionArterielle()) %>
                                                            </td>
                                                            <td>
                                                                <%= patient.getFrequenceCardiaque() %> bpm
                                                            </td>
                                                            <td>
                                                                <%= String.format(Locale.FRANCE, "%.2f °C" ,
                                                                    patient.getTemperature()) %>
                                                            </td>
                                                            <td>
                                                                <%= patient.getFrequenceRespiratoire() %> /min
                                                            </td>
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