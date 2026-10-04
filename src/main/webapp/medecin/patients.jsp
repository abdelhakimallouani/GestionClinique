<%@ page contentType="text/html;charset=UTF-8" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>

        <!DOCTYPE html>
        <html lang="fr">

        <head>
            <meta charset="UTF-8">
            <title>Patients en attente</title>

            <style>
                body {
                    font-family: Arial, sans-serif;
                    background: #f5f6fa;
                    margin: 0;
                    padding: 30px;
                }

                .container {
                    max-width: 1000px;
                    margin: auto;
                    background: white;
                    padding: 25px;
                    border-radius: 10px;
                }

                h1 {
                    margin-bottom: 25px;
                }

                table {
                    width: 100%;
                    border-collapse: collapse;
                }

                th,
                td {
                    padding: 12px;
                    border-bottom: 1px solid #ddd;
                    text-align: left;
                }

                th {
                    background: #f0f0f0;
                }

                .status {
                    padding: 6px 10px;
                    border-radius: 5px;
                    background: #fff3cd;
                    color: #856404;
                }

                .btn {
                    text-decoration: none;
                    padding: 8px 12px;
                    background: #007bff;
                    color: white;
                    border-radius: 5px;
                }
            </style>
        </head>

        <body>

            <div class="container">

                <h1>Patients en attente</h1>

                <c:choose>

                    <c:when test="${empty consultations}">

                        <p>Aucun patient en attente.</p>

                    </c:when>

                    <c:otherwise>

                        <table>

                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Nom</th>
                                    <th>Prénom</th>
                                    <th>Statut</th>
                                    <th>Action</th>
                                </tr>
                            </thead>

                            <tbody>

                                <c:forEach var="consultation" items="${consultations}">

                                    <tr>

                                        <td>
                                            ${consultation.id}
                                        </td>
                                        <td>
                                            ${consultation.patient.nom}
                                        </td>

                                        <td>
                                            ${consultation.patient.prenom}
                                        </td>

                                        <td>
                                            <span class="status">
                                                EN ATTENTE
                                            </span>
                                        </td>

                                        <td>
                                            <a class="btn"
                                                href="${pageContext.request.contextPath}/medecin/consultation?id=${consultation.id}">
                                                Consulter
                                            </a>
                                        </td>

                                    </tr>

                                </c:forEach>

                            </tbody>

                        </table>

                    </c:otherwise>

                </c:choose>

            </div>

        </body>

        </html>