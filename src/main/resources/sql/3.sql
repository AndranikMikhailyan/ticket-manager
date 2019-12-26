select p.id, p.last_name, p.first_name, p.patronymic
from tickets t
inner join passengers p on t.passenger_id = p.id
where quarter(t.departure_date) = quarter(current_date())
group by p.id
having count(p.id) > 10;