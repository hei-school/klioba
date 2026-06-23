document.addEventListener("DOMContentLoaded", function() {
  const cards = document.querySelectorAll('.club-card');
  const prevBtn = document.getElementById('page-prev');
  const nextBtn = document.getElementById('page-next');
  const pageInfo = document.getElementById('page-info');
  if (!cards.length || !prevBtn || !nextBtn || !pageInfo) return;

  const perPage = 8;
  let currentPage = 0;
  const totalPages = Math.ceil(cards.length / perPage);

  function showPage(page) {
    currentPage = page;
    cards.forEach((card, i) => {
      card.classList.toggle('hidden', i < page * perPage || i >= (page + 1) * perPage);
    });
    pageInfo.textContent = (page + 1) + ' / ' + totalPages;
    prevBtn.classList.toggle('opacity-30', page === 0);
    prevBtn.classList.toggle('cursor-not-allowed', page === 0);
    prevBtn.classList.toggle('pointer-events-none', page === 0);
    nextBtn.classList.toggle('opacity-30', page >= totalPages - 1);
    nextBtn.classList.toggle('cursor-not-allowed', page >= totalPages - 1);
    nextBtn.classList.toggle('pointer-events-none', page >= totalPages - 1);
  }

  prevBtn.addEventListener('click', () => { if (currentPage > 0) showPage(currentPage - 1); });
  nextBtn.addEventListener('click', () => { if (currentPage < totalPages - 1) showPage(currentPage + 1); });
  showPage(0);
});
