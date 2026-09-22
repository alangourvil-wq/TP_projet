document.addEventListener('DOMContentLoaded', function() {

    const params = new URLSearchParams(window.location.search);
    const idEntreprise = params.get("id");


    if (!idEntreprise) {
        console.error("Aucun id d'entreprise dans l'URL");
        return;
    }

    const messageNote = document.getElementById("message-note");

    var note_moyenne = 0;
    var nombre_notes = 0;

    fetch(`http://localhost:8080/api/entreprises/${idEntreprise}/moyenne`)
        .then((response) => {
            if (!response.ok) {
                throw new Error("Réponse non valide");
            }
            return response.json();
        })
        .then((moyenne) => {
            note_moyenne = moyenne;
            console.log("Moyenne récupérée :", note_moyenne);
        })
        .catch((error) => {
            console.error("Erreur lors de la récupération de la moyenne :", error);
        });
    
        fetch(`http://localhost:8080/api/entreprises/${idEntreprise}/notes`)
        .then((response) => {
            if (!response.ok) {
                throw new Error("Réponse non valide");
            }
            return response.json();
        })
        .then((nombre) => {
            nombre_notes = nombre;
            console.log("Nombre de notes récupéré :", nombre_notes);
        })
        .catch((error) => {
            console.error("Erreur lors de la récupération du nombre de notes :", error);
        });

    async function chargerDetail() {
        try {
            fetch("http://localhost:8080/api/entreprises?id=" + idEntreprise)
            .then((response) => {
                if (!response.ok) {
                    throw new Error("Réponse non valide");
                }
                return response.json();
            })
            .then((entreprises) => {
                const entreprise = entreprises[0];
                document.getElementById("nom-entreprise").textContent = entreprise.nom;
                document.getElementById("note-moyenne").textContent = note_moyenne.toFixed(2);
                document.getElementById("nombre-notes").textContent = nombre_notes;
                document.getElementById("lieu-entreprise").textContent = entreprise.lieu;
                document.getElementById("description-entreprise").textContent = entreprise.description;
                document.getElementById("image-entreprise").src = entreprise.imageUrl;
                afficherEmployes(entreprise.employes || []);

            })
            .catch((error) => {
                console.error("Erreur lors de la récupération des détails de l'entreprise :", error);
            });
        } catch (err) {
            console.error("Erreur lors de la récupération des détails de l'entreprise :", err);
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

        } catch (err) {
            console.error("Impossible d'envoyer la note :", err);
            messageNote.textContent = "Erreur lors de l'envoi de la note.";
            messageNote.style.color = "red";
        }
    }

    document.getElementById("btn-valider-note").addEventListener("click", async () => {
        const radioChecked = document.querySelector('#etoiles input[name="note"]:checked');

        if (!radioChecked) {
            messageNote.textContent = "Sélectionnez d'abord une note.";
            messageNote.style.color = "orange";
            return;
        }
        await envoyerNote(parseInt(radioChecked.value, 10));
        window.location.reload();
    });

    chargerDetail();
});