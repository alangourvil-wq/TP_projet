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
    var repartition_notes = [];

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

    fetch(`http://localhost:8080/api/entreprises/${idEntreprise}/repartition-notes`)
        .then((response) => {
            if (!response.ok) {
                throw new Error("Réponse non valide");
            }
            return response.json();
        })
        .then((repartition) => {
            repartition_notes = repartition;
            console.log("Répartition des notes récupérée :", repartition_notes);
        })
        .catch((error) => {
            console.error("Erreur lors de la récupération de la répartition des notes :", error);
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
                if(document.getElementById("nom-entreprise")) {
                    document.getElementById("nom-entreprise").textContent = entreprise.nom;
                }
                if(document.getElementById("note-moyenne")) {
                    document.getElementById("note-moyenne").textContent = note_moyenne.toFixed(2);
                }
                if(document.getElementById("nombre-notes")) {
                    document.getElementById("nombre-notes").textContent = nombre_notes;
                }
                if(document.getElementById("lieu-entreprise")) {
                    document.getElementById("lieu-entreprise").textContent = entreprise.lieu;
                }
                if(document.getElementById("description-entreprise")) {
                    document.getElementById("description-entreprise").textContent = entreprise.description;
                }
                if(document.getElementById("image-entreprise")) {
                    document.getElementById("image-entreprise").src = entreprise.imageUrl;
                }
                if(document.getElementById("nombre-employes")) {
                    document.getElementById("nombre-employes").textContent = entreprise.employes.length;
                }
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