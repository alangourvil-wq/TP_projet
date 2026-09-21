document.addEventListener('DOMContentLoaded', function() {

    const params = new URLSearchParams(window.location.search);
    const idEntreprise = params.get("id");

    if (!idEntreprise) {
        console.error("Aucun id d'entreprise dans l'URL");
        return;
    }

    const messageNote = document.getElementById("message-note");

    async function chargerDetail() {
        try {
            const res = await fetch(`http://localhost:8080/api/entreprises/${idEntreprise}`);
            if (!res.ok) throw new Error("Erreur HTTP " + res.status);
            const entreprise = await res.json();

            document.getElementById("nom-entreprise").textContent = entreprise.nom;
            document.getElementById("note-moyenne").textContent =
                entreprise.moyenneNote.toFixed(1);

            afficherEmployes(entreprise.employes || []);

        } catch (err) {
            console.error("Impossible de charger l'entreprise :", err);
            messageNote.textContent = "Erreur de chargement de l'entreprise.";
        }
    }

    function afficherEmployes(employes) {
        const table = document.getElementById("table-employes");
        table.querySelectorAll("tr:not(:first-child)").forEach(tr => tr.remove());

        employes.forEach(emp => {
            const row = table.insertRow();
            row.insertCell(0).textContent = emp.nom;
        });
    }

    async function envoyerNote(valeur) {
        try {
            const res = await fetch(`http://localhost:8080/api/entreprises/${idEntreprise}/notes`, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ valeur: valeur })
            });

            if (!res.ok) {
                const texte = await res.text();
                throw new Error(`HTTP ${res.status} - ${texte}`);
            }

            messageNote.textContent = "Note enregistrée, merci !";
            messageNote.style.color = "green";
            chargerDetail();

        } catch (err) {
            console.error("Impossible d'envoyer la note :", err);
            messageNote.textContent = "Erreur lors de l'envoi de la note.";
            messageNote.style.color = "red";
        }
    }

    // Bouton explicite : on lit la valeur cochée seulement au clic
    document.getElementById("btn-valider-note").addEventListener("click", () => {
        const radioChecked = document.querySelector('#etoiles input[name="note"]:checked');

        if (!radioChecked) {
            messageNote.textContent = "Sélectionnez d'abord une note.";
            messageNote.style.color = "orange";
            return;
        }

        envoyerNote(parseInt(radioChecked.value, 10));
    });

    chargerDetail();
});