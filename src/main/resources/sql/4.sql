select p.id, p.last_name, p.first_name, p.patronymic
from tickets t
inner join passengers p on t.passenger_id = p.id
where dayofyear(t.departure_date) between dayofyear(p.birth_day) - 3 and dayofyear(p.birth_day) + 3;