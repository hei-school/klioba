document.addEventListener("DOMContentLoaded", function() {
  const cards = document.querySelectorAll('.club-card');
  const prevBtn = document.getElementById('page-prev');
  const nextBtn = document.getElementById('page-next');
  const pageInfo = document.getElementById('page-info');
  const searchInput = document.getElementById('club-search');
  const noResults = document.getElementById('no-search-results');
  if (!cards.length || !prevBtn || !nextBtn || !pageInfo) return;

  const perPage = 8;
  let currentPage = 0;

  function visibleCards() {
    return Array.from(cards).filter(c => !c.classList.contains('search-hidden'));
  }

  function totalPages() {
    return Math.ceil(visibleCards().length / perPage) || 1;
  }

  function showPage(page) {
    const visible = visibleCards();
    const pages = totalPages();
    currentPage = Math.min(Math.max(page, 0), pages - 1);

    cards.forEach(c => c.classList.add('hidden'));
    visible.forEach((c, i) => {
      c.classList.toggle('hidden', i < currentPage * perPage || i >= (currentPage + 1) * perPage);
    });

    pageInfo.textContent = visible.length > 0 ? (currentPage + 1) + ' / ' + pages : '0 / 0';
    prevBtn.classList.toggle('opacity-30', currentPage <= 0);
    prevBtn.classList.toggle('cursor-not-allowed', currentPage <= 0);
    prevBtn.classList.toggle('pointer-events-none', currentPage <= 0);
    nextBtn.classList.toggle('opacity-30', currentPage >= pages - 1);
    nextBtn.classList.toggle('cursor-not-allowed', currentPage >= pages - 1);
    nextBtn.classList.toggle('pointer-events-none', currentPage >= pages - 1);

    if (noResults) {
      noResults.classList.toggle('hidden', visible.length > 0);
    }
  }

  function filterCards() {
    const query = searchInput ? searchInput.value.toLowerCase().trim() : '';
    cards.forEach(c => {
      const nameEl = c.querySelector('h3');
      const name = (nameEl ? nameEl.textContent : '').toLowerCase();
      c.classList.toggle('search-hidden', query.length > 0 && !name.includes(query));
    });
    currentPage = 0;
    showPage(0);
  }

  if (searchInput) {
    searchInput.addEventListener('input', filterCards);
  }

  prevBtn.addEventListener('click', () => { if (currentPage > 0) showPage(currentPage - 1); });
  nextBtn.addEventListener('click', () => { if (currentPage < totalPages() - 1) showPage(currentPage + 1); });

  showPage(0);
});
