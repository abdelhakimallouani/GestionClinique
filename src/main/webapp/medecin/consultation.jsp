<form method="post"
      action="${pageContext.request.contextPath}/consultations">

    <input type="hidden"
           name="action"
           value="close">

    <input type="hidden"
           name="id"
           value="${consultation.id}">

    <label>Motif</label>
    <textarea name="motif"></textarea>

    <label>Observations</label>
    <textarea name="observations"></textarea>

    <label>Diagnostic</label>
    <textarea name="diagnostic"></textarea>

    <label>Traitement</label>
    <textarea name="traitement"></textarea>

    <p>Coût : 150 DH</p>

    <button type="submit">
        Clôturer
    </button>

</form>