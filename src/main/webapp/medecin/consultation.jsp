<%@ page contentType="text/html;charset=UTF-8" %>

       <!DOCTYPE html>
       <html lang="fr">

       <head>
              <meta charset="UTF-8">
              <title>Détails du patient</title>
       </head>

       <body>

              <h1>Détails du patient</h1>

              <h2>Informations du patient</h2>

              <p>
                     <strong>Nom :</strong>
                     ${patient.nom}
              </p>

              <p>
                     <strong>Prénom :</strong>
                     ${patient.prenom}
              </p>

              <p>
                     <strong>Date de naissance :</strong>
                     ${patient.dateNaissance}
              </p>

              <p>
                     <strong>N° sécurité sociale :</strong>
                     ${patient.numeroSecuriteSociale}
              </p>


              <h2>Signes vitaux</h2>

              <p>
                     <strong>Tension artérielle :</strong>
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


              <h2>Consultation</h2>

              <p>
                     <strong>Statut :</strong>
                     ${consultation.statut}
              </p>

              <p>
                     <strong>Motif :</strong>
                     ${consultation.motif}
              </p>

              <p>
                     <strong>Observations :</strong>
                     ${consultation.observations}
              </p>

              <p>
                     <strong>Diagnostic :</strong>
                     ${consultation.diagnostic}
              </p>

              <p>
                     <strong>Traitement :</strong>
                     ${consultation.traitement}
              </p>

              <p>
                     <strong>Coût :</strong>
                     ${consultation.cout} DH
              </p>

              <p>
                     <strong>Date consultation :</strong>
                     ${consultation.dateConsultation}
              </p>


              <br>

              <a href="${pageContext.request.contextPath}/medecin/consultation/edit?id=${consultation.id}">
                     <button type="button">
                            Faire la consultation
                     </button>
              </a>


              <br><br>

              <a href="${pageContext.request.contextPath}/medecin/patients">
                     Retour aux patients
              </a>

       </body>

       </html>