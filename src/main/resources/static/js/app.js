async function shortenUrl() {
    const urlInput = document.getElementById('urlInput');
    const result = document.getElementById('result');
    const shortenedUrlDiv = document.getElementById('shortenedUrl');

    const originalUrl = urlInput.value.trim();

    if (!originalUrl) {
        alert('Please enter a URL');
        return;
    }

    if (!isValidUrl(originalUrl)) {
        alert('Please enter a valid URL');
        return;
    }

    // Simulate API call with loading state
    const btn = document.querySelector('.btn');
    const originalText = btn.textContent;
    btn.textContent = 'Shortening...';
    btn.disabled = true;

    try {
        const url = window.location.origin + "/create-link"
        console.log(url)
        const response = await fetch(url, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ link: originalUrl }),
        });

        if (response.ok) {
            const data = await response.json();
            shortenedUrlDiv.textContent = data.shortUrl;
            result.classList.add('show');

            btn.textContent = originalText;
            btn.disabled = false;
        }
    }catch (e){
        console.log(e.message)
    }

}

function isValidUrl(string) {
    try {
        new URL(string);
        return true;
    } catch (_) {
        return false;
    }
}

function copyUrl() {
    const urlText = document.getElementById('shortenedUrl').textContent;
    navigator.clipboard.writeText(urlText).then(() => {
        const copyBtn = document.querySelector('.copy-btn');
        const originalText = copyBtn.textContent;
        copyBtn.textContent = 'Copied!';
        copyBtn.style.background = '#10b981';

        setTimeout(() => {
            copyBtn.textContent = originalText;
            copyBtn.style.background = '#667eea';
        }, 2000);
    });
}

// Handle Enter key press
document.getElementById('urlInput').addEventListener('keypress', async function(e) {
    if (e.key === 'Enter') {
        await shortenUrl();
    }
});